from collections import deque


def bfs(start_node, end_node):
    MAX = 100000
    queue = deque([start_node])
    array = [0] * (MAX + 1)

    while queue:
        curr_node = queue.popleft()

        if curr_node == end_node:
            return array[curr_node]

        for next_node in [curr_node - 1, curr_node + 1, curr_node * 2]:
            if 0 <= next_node <= MAX and not array[next_node]:
                array[next_node] = array[curr_node] + 1
                queue.append(next_node)

    return -1


def main():
    subin, sister = map(int, input().split())
    print(bfs(subin, sister))


main()
