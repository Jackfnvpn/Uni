def MergeSort(L: list, start: int, end: int) -> None:
    if start < end:
        medium = (start + end) // 2
        MergeSort(L, start, medium)
        MergeSort(L, medium + 1, end)
        Merge(L, start, medium, end)


def Merge(L: list, start: int, end_1: int, end_2: int) -> None:
    X = [0] * (end_2 - start + 1)

    i = 0
    s1 = start
    s2 = end_1 + 1

    while s1 <= end_1 and s2 <= end_2:
        if L[s1] <= L[s2]:
            X[i] = L[s1]
            s1 += 1
        else:
            X[i] = L[s2]
            s2 += 1
        i += 1

    while s1 <= end_1:
        X[i] = L[s1]
        s1 += 1
        i += 1

    while s2 <= end_2:
        X[i] = L[s2]
        s2 += 1
        i += 1

    for i in range(start, end_2 + 1):
        L[i] = X[i - start]
