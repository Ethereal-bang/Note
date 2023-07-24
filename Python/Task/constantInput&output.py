sum = 0
count = 0
countPositive = 0
countNegative = 0
while 1:
    a = int(input())
    if a == 0:
        break
    sum += a
    count += 1
    if a > 0:
        countPositive += 1
    else:
        countNegative += 1
aver = float(sum / count)
print("{}\n{}\n{}\n".format(aver, countPositive, countNegative))
