### *DEF:*  
$H_i$=($r[i],v[i],b[i]$)
### *DEF:* Sottoproblema
$OPT[i]: x=min(H_i)$ $t.c$ $x.col \neq OPT[i-1].col$

### *DEF:* Caso Base
$OPT[1]: min(H_1)$

### *DEF:* Recursion Formula  

$OPT[i]=${  
+ $min (v[i],b[i])$ $if$  $OPT[i-1].col = r.col$  
+ $min(r[i],b[i])$ $if$ $OPT[i-1].col = v.col$
+ $min(v[i],r[i])$ $if$ $OPT[i-1].col = b.col$

}

### Code 
```python
class House:
    def __init__(self) -> None:
        self.r = [(7, "r"), (6, "r"), (7, "r"), (8, "r"), (9, "r"), (20, "r")]
        self.v = [(3, "v"), (8, "v"), (9, "v"), (22, "v"), (12, "v"), (8, "v")]
        self.b = [(16, "b"), (10, "b"), (4, "b"), (2, "b"), (5, "b"), (7, "b")]
        self.OPT = [(0, "")] * 6

    def computeOPT(self):
        self.OPT[0] = min(self.r[0], self.v[0], self.b[0])

        for i in range(1, 6):
            if self.OPT[i - 1][1] == "r":
                self.OPT[i] = min(self.v[i], self.b[i])
            elif self.OPT[i - 1][1] == "v":
                self.OPT[i] = min(self.r[i], self.b[i])
            else:
                self.OPT[i] = min(self.v[i], self.r[i])

        return self.OPT


house = House()
print(house.computeOPT())
```
