#include <iostream>
#include <vector>
#include <algorithm>
#include <limits>

using namespace std;

const int INF = numeric_limits<int>::max();
const int NEG_INF = numeric_limits<int>::min();

const int NUMBER_OF_BINS = 6;
const int TOTAL_BINS = 2 * NUMBER_OF_BINS + 2;

struct Board
{
    vector<int> bins;

    Board()
    {
        bins = vector<int>(TOTAL_BINS, 4);
        bins[NUMBER_OF_BINS] = 0;
        bins[NUMBER_OF_BINS + 1 + NUMBER_OF_BINS] = 0;
    }

    bool is_game_over() const
    {
        bool player1_side_empty = true, player2_side_empty = true;
        for (int i = 0; i < NUMBER_OF_BINS; ++i)
        {
            if (bins[i] != 0)
                player1_side_empty = false;
            if (bins[i + NUMBER_OF_BINS + 1] != 0)
                player2_side_empty = false;
        }
        return player1_side_empty || player2_side_empty;
    }

    bool make_move(int bin_index, int player)
    {
        int storage_index = (player == 1) ? NUMBER_OF_BINS : TOTAL_BINS - 1;
        int opponent_storage = (player == 1) ? TOTAL_BINS - 1 : NUMBER_OF_BINS;

        int number_of_stones = bins[bin_index];
        bins[bin_index] = 0;
        int current_index = bin_index;

        while (number_of_stones > 0)
        {
            current_index = (current_index + 1) % TOTAL_BINS;
            if (current_index == opponent_storage)
                continue;
            bins[current_index]++;
            number_of_stones--;
        }

        // returning true for player getting another move
        if (current_index == storage_index)
            return true;
        return false;
    }

    int evaluate(int player, int heuristic_type) const
    {
        int player_storage = (player == 1) ? NUMBER_OF_BINS : TOTAL_BINS - 1;
        int opponent_storage = (player == 1) ? TOTAL_BINS - 1 : NUMBER_OF_BINS;

        int score = 0;
        if (heuristic_type == 1)
        {
            score = bins[player_storage] - bins[opponent_storage];
        }
        else if (heuristic_type == 2)
        {
            int stones_on_my_side = 0, stones_on_opponent_side = 0;
            if (player == 1)
            {
                for (int i = 0; i < NUMBER_OF_BINS; ++i)
                    stones_on_my_side += bins[i];
                for (int i = NUMBER_OF_BINS + 1; i < TOTAL_BINS - 1; ++i)
                    stones_on_opponent_side += bins[i];
            }
            else
            {
                for (int i = NUMBER_OF_BINS + 1; i < TOTAL_BINS - 1; ++i)
                    stones_on_my_side += bins[i];
                for (int i = 0; i < NUMBER_OF_BINS; ++i)
                    stones_on_opponent_side += bins[i];
            }
            score = (bins[player_storage] - bins[opponent_storage]) + (stones_on_my_side - stones_on_opponent_side);
        }
        return score;
    }
};

int minimax(Board board, int depth, int alpha, int beta, bool is_maximizing, int player, int heuristic_type)
{
    if (depth == 0 || board.is_game_over())
    {
        return board.evaluate(player, heuristic_type);
    }

    if (is_maximizing)
    {
        int max_eval = NEG_INF;
        for (int i = 0; i < NUMBER_OF_BINS; ++i)
        {
            Board temp_board = board;
            if (temp_board.bins[i] > 0 && temp_board.make_move(i, player))
            {
                int eval = minimax(temp_board, depth, alpha, beta, true, player, heuristic_type);
                max_eval = max(max_eval, eval);
                alpha = max(alpha, eval);
                if (beta <= alpha)
                    break;
            }
            else
            {
                int eval = minimax(temp_board, depth - 1, alpha, beta, false, player, heuristic_type);
                max_eval = max(max_eval, eval);
                alpha = max(alpha, eval);
                if (beta <= alpha)
                    break;
            }
        }
        return max_eval;
    }
    else
    {
        int min_eval = INF;
        int opponent = (player == 1) ? 2 : 1;
        for (int i = 0; i < NUMBER_OF_BINS; ++i)
        {
            Board temp_board = board;
            if (temp_board.bins[i + NUMBER_OF_BINS + 1] > 0 && temp_board.make_move(i + NUMBER_OF_BINS + 1, opponent))
            {
                int eval = minimax(temp_board, depth, alpha, beta, false, opponent, heuristic_type);
                min_eval = min(min_eval, eval);
                beta = min(beta, eval);
                if (beta <= alpha)
                    break;
            }
            else
            {
                int eval = minimax(temp_board, depth - 1, alpha, beta, true, player, heuristic_type);
                min_eval = min(min_eval, eval);
                beta = min(beta, eval);
                if (beta <= alpha)
                    break;
            }
        }
        return min_eval;
    }
}

int main()
{
    Board board;
    int current_player = 2;
    int game_tree_depth = 5;
    int heuristic_type = 1;

    int best_move = minimax(board, game_tree_depth, NEG_INF, INF, true, current_player, heuristic_type);
    cout << "Best move evaluation: " << best_move << endl;

    return 0;
}