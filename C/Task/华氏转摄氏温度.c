#include<stdio.h>
int main(void)
{
    int f;
    double c;

    for (f = -40; f <= 110; f += 10)
    {
        c = 5.0 / 9 * (f - 32); // 注意：用 5.0 参与运算才能得小数
        printf("%4d\t%6.1f\n", f,  c);
    }
}
  /*
  某人在国外留学，不熟悉当地天气预报中的华氏温度值，请编程按每隔10°输出从-40°到110°之间的华氏温度到摄氏温度的对照表，以方便他对照查找。已知华氏和摄氏温度的转换公式为:
       C=5/9*(F-32)
  其中，C表示摄氏温度，F表示华氏温度。
  ***输入提示信息：无
  ***输入数据格式：无
  ***输出数据格式："%4d\t%6.1f\n"
  注：%4d对应华氏温度输出，%6.1f对应摄氏温度输出
  程序运行示例：
   -40     -40.0
   -30     -34.4
   -20     -28.9
   -10     -23.3
     0     -17.8
    10     -12.2
    20      -6.7
    30      -1.1
    40       4.4
    50      10.0
    60      15.6
    70      21.1
    80      26.7
    90      32.2
   100      37.8
   110      43.3
*/
