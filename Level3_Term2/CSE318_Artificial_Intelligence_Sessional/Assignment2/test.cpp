#include <iostream>
#include <vector>
#include <algorithm>
#include <limits>
#include <fstream>
#include <cstdlib>
#include <ctime>

using namespace std;

// Defining constants for easier coding
const int INF = numeric_limits<int>::max();
const int NEG_INF = numeric_limits<int>::min();

const int PLAYER_1 = 0;
const int PLAYER_2 = 1;

const int NUMBER_OF_BINS = 6;
const int TOTAL_BINS = NUMBER_OF_BINS + NUMBER_OF_BINS + 2;

const int PLAYER_1_STORAGE = NUMBER_OF_BINS;
const int PLAYER_2_STORAGE = TOTAL_BINS - 1;

struct Board
{
    vector<int> bins;

    Board()
    {
        bins = vector<int>(TOTAL_BINS, 4);
        bins[PLAYER_1_STORAGE] = 0; // Player 1's storage
        bins[PLAYER_2_STORAGE] = 0; // Player 2's storage
    }

    void print_board(ofstream &output_file)
    { // Take output_file as parameter
        output_file << "Board state" << endl;
        for (int i = TOTAL_BINS - 2; i >= NUMBER_OF_BINS + 1; --i)
        {
            output_file << bins[i] << " ";
        }
        output_file << endl;

        output_file << bins[PLAYER_2_STORAGE] << "        " << bins[PLAYER_1_STORAGE] << endl;

        for (int i = 0; i < NUMBER_OF_BINS; ++i)
        {
            output_file << bins[i] << " ";
        }
        output_file << endl
                    << endl;
    }

    bool is_game_over()
    {
        bool player1_side_empty = all_of(bins.begin(), bins.begin() + NUMBER_OF_BINS, [](int stones)
                                         { return stones == 0; });
        bool player2_side_empty = all_of(bins.begin() + NUMBER_OF_BINS + 1, bins.begin() + TOTAL_BINS - 1, [](int stones)
                                         { return stones == 0; });
        return player1_side_empty || player2_side_empty;
    }

    void collect_remaining_stones()
    {
        for (int i = 0; i < NUMBER_OF_BINS; ++i)
        {
            bins[PLAYER_1_STORAGE] += bins[i];
            bins[i] = 0;
        }
        for (int i = NUMBER_OF_BINS + 1; i < TOTAL_BINS - 1; ++i)
        {
            bins[PLAYER_2_STORAGE] += bins[i];
            bins[i] = 0;
        }
    }

    bool make_move(int bin_index, int player)
    {
        if (bin_index < 0 || bin_index >= TOTAL_BINS)
            return false;

        int storage_index = (player == PLAYER_1) ? PLAYER_1_STORAGE : PLAYER_2_STORAGE;
        int opponent_storage = (player == PLAYER_1) ? PLAYER_2_STORAGE : PLAYER_1_STORAGE;

        int number_of_stones = bins[bin_index];
        if (number_of_stones == 0)
            return false; // No move possible

        bins[bin_index] = 0;
        int current_index = bin_index;

        // Distribute stones in a circular fashion
        while (number_of_stones > 0)
        {
            current_index = (current_index + 1) % TOTAL_BINS;
            if (current_index == opponent_storage)
                continue; // Skip opponent's storage
            bins[current_index]++;
            number_of_stones--;
        }

        // Capture rule: If last stone lands on the player's empty bin
        if ((player == PLAYER_1 && current_index < NUMBER_OF_BINS && bins[current_index] == 1) ||
            (player == PLAYER_2 && current_index > NUMBER_OF_BINS && current_index < TOTAL_BINS - 1 && bins[current_index] == 1))
        {
            int capture_index = (player == PLAYER_1) ? (TOTAL_BINS - 2 - current_index) : (TOTAL_BINS - 2 - current_index);
            bins[storage_index] += bins[capture_index] + 1; // Capture opponent's stones and add last stone
            bins[capture_index] = 0;                        // Empty the opponent's captured bin
            bins[current_index] = 0;                        // Empty the player's empty bin
        }

        // Return true if the player gets another turn (last stone in their storage)
        return current_index == storage_index;
    }

    int evaluate(int player, int heuristic_type) const
    {
        int player_storage = (player == PLAYER_1) ? PLAYER_1_STORAGE : PLAYER_2_STORAGE;
        int opponent_storage = (player == PLAYER_1) ? PLAYER_2_STORAGE : PLAYER_1_STORAGE;

        int score = 0;

        // Heuristic 1: Simple storage difference
        if (heuristic_type == 1)
        {
            score = bins[player_storage] - bins[opponent_storage];
        }
        // Heuristic 2: Storage difference + stones on the player's side
        else if (heuristic_type == 2)
        {
            int stones_on_my_side = 0, stones_on_opponent_side = 0;
            for (int i = 0; i < NUMBER_OF_BINS; ++i)
                stones_on_my_side += bins[i];
            for (int i = NUMBER_OF_BINS + 1; i < TOTAL_BINS - 1; ++i)
                stones_on_opponent_side += bins[i];
            score = (bins[player_storage] - bins[opponent_storage]) + (stones_on_my_side - stones_on_opponent_side);
        }
        return score;
    }

    int get_winner()
    {
        if (bins[PLAYER_1_STORAGE] > bins[PLAYER_2_STORAGE])
            return PLAYER_1;
        if (bins[PLAYER_2_STORAGE] > bins[PLAYER_1_STORAGE])
            return PLAYER_2;
        return 100; // Tie
    }
};

int minimax(Board &board, int depth, int alpha, int beta, int maxPlayer, int heuristic)
{
    if (depth <= 0 || board.is_game_over())
    {
        return board.evaluate(maxPlayer, heuristic);
    }

    if (maxPlayer == PLAYER_1)
    {
        int maxValue = NEG_INF;
        for (int i = 0; i < NUMBER_OF_BINS; i++)
        {
            if (board.bins[i] == 0)
                continue; // Skip empty bins
            Board temp_board = board;
            bool another_turn = temp_board.make_move(i, PLAYER_1);
            if (another_turn)
            {
                maxValue = max(maxValue, minimax(temp_board, depth, alpha, beta, PLAYER_1, heuristic));
            }
            else
            {
                maxValue = max(maxValue, minimax(temp_board, depth - 1, alpha, beta, PLAYER_2, heuristic));
            }
            alpha = max(alpha, maxValue);
            if (beta <= alpha)
                break; // Beta cut-off
        }
        return maxValue;
    }
    else
    {
        int minValue = INF;
        for (int i = 0; i < NUMBER_OF_BINS; i++)
        {
            if (board.bins[NUMBER_OF_BINS + i + 1] == 0)
                continue; // Skip empty bins
            Board temp_board = board;
            bool another_turn = temp_board.make_move(NUMBER_OF_BINS + i + 1, PLAYER_2);
            if (another_turn)
            {
                minValue = min(minValue, minimax(temp_board, depth, alpha, beta, PLAYER_2, heuristic));
            }
            else
            {
                minValue = min(minValue, minimax(temp_board, depth - 1, alpha, beta, PLAYER_1, heuristic));
            }
            beta = min(beta, minValue);
            if (beta <= alpha)
                break; // Alpha cut-off
        }
        return minValue;
    }
}

int get_best_move(Board &board, int depth, int heuristic, int player)
{
    int best_bin_index = -1;
    int best_bin_value = (player == PLAYER_1) ? NEG_INF : INF;

    // Only check bins on the player's side
    for (int i = 0; i < NUMBER_OF_BINS; i++)
    {
        if (player == PLAYER_1)
        {
            // Check Player 1's bins
            if (board.bins[i] == 0)
                continue; // Skip empty bins

            Board temp_board = board;
            bool another_turn = temp_board.make_move(i, PLAYER_1);
            int move_value = minimax(temp_board, depth, NEG_INF, INF, another_turn ? PLAYER_1 : PLAYER_2, heuristic);

            if (move_value > best_bin_value)
            {
                best_bin_value = move_value;
                best_bin_index = i;
            }
        }
        else
        {
            // Check Player 2's bins
            if (board.bins[NUMBER_OF_BINS + i + 1] == 0)
                continue; // Skip empty bins

            Board temp_board = board;
            bool another_turn = temp_board.make_move(NUMBER_OF_BINS + i + 1, PLAYER_2);
            int move_value = minimax(temp_board, depth, NEG_INF, INF, another_turn ? PLAYER_2 : PLAYER_1, heuristic);

            if (move_value < best_bin_value)
            {
                best_bin_value = move_value;
                best_bin_index = NUMBER_OF_BINS + i + 1; // Store the bin index for Player 2
            }
        }
    }

    return best_bin_index; // Return the index of the best move
}

void computer_vs_computer_simulation(Board &board, int depth, int heuristic1, int heuristic2, ofstream &output_file)
{

    // Randomly choosing the player to make the first move
    // srand(time(0));
    // int current_player = rand() % 2;

    while (!board.is_game_over())
    {
        board.print_board(output_file);

        // Player 1's turn
        int player1_move = get_best_move(board, depth, heuristic1, PLAYER_1);
        output_file << "Player 1 move from bin: " << player1_move << endl;
        board.make_move(player1_move, PLAYER_1);

        if (board.is_game_over())
            break;

        // Player 2's turn
        board.print_board(output_file);
        int player2_move = get_best_move(board, depth, heuristic2, PLAYER_2);
        output_file << "Player 2 move from bin: " << player2_move - NUMBER_OF_BINS - 1 << endl;
        board.make_move(player2_move, PLAYER_2);
    }

    board.collect_remaining_stones();
    board.print_board(output_file);

    if(board.get_winner()==100) output_file << "Match Drawn" << endl;
    else output_file << "Winner: Player " << board.get_winner() + 1 << endl;
}

int main()
{
    ofstream output_file("game_states.txt");

    int depth = 4;
    int heuristic1 = 2;
    int heuristic2 = 1;
    Board board;

    computer_vs_computer_simulation(board, depth, heuristic1, heuristic2, output_file);
    output_file.close();

    return 0;
}

// g++ -o test test.cpp
// ./test