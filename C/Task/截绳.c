#include<stdio.h>
float FindLengthOfRope(float m, int n)
{
    int i = 1;
    float len = m;
    
    do
    {
        len *= 0.5;
        i++;
    } while (i <= n);
    
    return len;
}
int main(void)
{
    int n;
    float m;
    
    printf("Input length and days:");
    scanf("%f,%d", &m, &n);
    
    printf("length=%.5f\n", FindLengthOfRope(m, n));
}
/*
  我国古代著作《庄子》中记载道：“一尺之捶，日取其半，万世不竭”。其含义是：对于一尺的东西，今天取其一半，明天取其一半的一半，后天再取其一半的一半的一半总有一半留下，所以永远也取不尽。请编写一个程序，使其可以计算出一条长为m的绳子，在n天之后剩下的长度。

  运行结果示例1：
  Input length and days:12,5
  length=0.37500

  运行结果示例2：
  Input length and days:57.6,7
  length=0.45000

  输入提示信息："Input length and days:"
  输入格式: "%f,%d"
  输出格式："length=%.5f\n"
*/
