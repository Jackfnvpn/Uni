class IntervalScheduling:
    def __init__(self, job) -> None:
        self.list = job

    def MergeSort(self, L: list, start: int, end: int) -> None:
        if start < end:
            medium = (start + end) // 2
            self.MergeSort(L, start, medium)
            self.MergeSort(L, medium + 1, end)
            self.Merge(L, start, medium, end)

    def Merge(self, L: list, start: int, end_1: int, end_2: int) -> None:
        X = [0] * (end_2 - start + 1)

        i = 0
        s1 = start
        s2 = end_1 + 1

        while s1 <= end_1 and s2 <= end_2:
            if L[s1][1] <= L[s2][1]:
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

    def BinarySearch(self, L: list, i: int, j: int, x: int):
        if i > j:
            return -1
        else:
            m = (i + j) // 2
            if L[m][1] <= x[0]:
                if L[m + 1][1] <= x[0]:
                    return self.BinarySearch(L, m + 1, j, x)
                else:
                    return m
            else:
                return self.BinarySearch(L, i, m - 1, x)

    def _computeP(self):
        p = [0] * len(self.list)
        for x in range(len(self.list)):
            m = self.BinarySearch(self.list, 0, len(self.list) - 1, self.list[x])
            p[x] = m + 1
        return p

    def Bottom_Up(self):
        # p = [0] * len(self.list)
        self.MergeSort(self.list, 0, len(self.list) - 1)
        # print(self.list)
        p = self._computeP()
        M = [0] * (len(p) + 1)
        M[0] = 0
        for j in range(1, len(p) + 1):
            M[j] = max(M[j - 1], self.list[j - 1][2] + M[p[j - 1]])

        return M[len(M) - 1]

    def printJobs(self):
        self.MergeSort(self.list, 0, len(self.list) - 1)
        print(self.list)


jobs = [
    (0, 6, 1),
    (1, 4, 10),
    (3, 5, 3),
    (3, 8, 2),
    (4, 7, 40),
    (5, 9, 15),
    (6, 10, 67),
    (4, 11, 8),
]
is_instance = IntervalScheduling(jobs)
is_instance.printJobs()

y = is_instance.Bottom_Up()
print(y)
