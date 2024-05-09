class IntervalScheduling:
    def __init__(self, jobs) -> None:
        self.jobs = jobs

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

    def Earliest_Finish_Time(self):
        self.MergeSort(self.jobs, 0, len(self.jobs) - 1)
        S = []
        index = 0
        for x in range(len(self.jobs)):
            if len(S) == 0:
                S.append(self.jobs[x])
                index += 1
            else:
                if S[index - 1][1] <= self.jobs[x][0]:
                    S.append(self.jobs[x])
                    index += 1
        return S


jobs = [(0, 6), (1, 4), (3, 5), (3, 8), (4, 7), (5, 9), (6, 10), (8, 11)]
isinstance = IntervalScheduling(jobs)
S = isinstance.Earliest_Finish_Time()
print(S)
