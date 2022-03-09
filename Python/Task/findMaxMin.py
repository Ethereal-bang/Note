m = int(input())
n = int(input())

x = m
y = n
# 辗转相除法
if(x > y):  # 使y为较大值
    temp = x
    x = y
    y = temp
while (x != 0):
    r = y % x
    y = x
    x = r
max = y
min = m * n // max

print("{} {}".format(max, min))
