def isPrime(n): # 函数功能：n 为素数则返回 1，不为则返回 -1
    for i in range(2, n):
        if n % i == 0 and n > 2:
            return -1

    return 1

n = int(input())
num = []
for i in range(2, n+1):
    res = isPrime(i)
    if res == 1:
        num.append(i)


for i in num:
    print(i, end = ' ')
