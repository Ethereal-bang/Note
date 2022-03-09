while True: # 反复输入成绩进行转换
    score = float(input())  # 输入浮点数表示百分制成绩
    if score < 0:
        print("end")
        break
    elif score > 100:
        print("data error!")
    elif score >= 90:
        print("A")
    elif score >= 80:
        print("B")
    elif score >= 70:
        print("C")
    elif score >= 60:
        print("D")
    else:
        print("E")
