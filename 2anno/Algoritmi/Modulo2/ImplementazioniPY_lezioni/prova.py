def min_cost_to_monitor_corridor(cost_matrix):
    n = len(cost_matrix)
    dp = [float('inf')] * n

    # Initialize the last room cost
    dp[-1] = cost_matrix[-1][0]

    # Fill the dp table from right to left
    for i in range(n-2, -1, -1):
        for r in range(n-i):
            if i + r + 1 < n:
                dp[i] = min(dp[i], cost_matrix[i][r] + dp[i + r + 1])
            else:
                dp[i] = min(dp[i], cost_matrix[i][r])

    return dp[0]

# Example cost matrix
cost_matrix = [
    [1, 3, 6, 10, 15],
    [2, 4, 7, 11, float('inf')],
    [3, 5, 8, float('inf'), float('inf')],
    [4, 6, float('inf'), float('inf'), float('inf')],
    [5, float('inf'), float('inf'), float('inf'), float('inf')],
]

print("Minimum cost to monitor the corridor:", min_cost_to_monitor_corridor(cost_matrix))

