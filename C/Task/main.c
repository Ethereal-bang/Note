#include<stdio.h>
#define N 100
int FindLength(char *str);
int main(void)
{
    char str[N];
    int length;
    printf("Enter string:");
    scanf("%s", &str);
    length = FindLength(&str[0]);
    printf("string length=%d\n", length);
}
// �������ܣ����ַ����ĳ���
int FindLength(char *str)
{
    int i;
    for(i = 0; str[i] != '\0'; i++); // for����ֱ�Ӽ��� ; ������ѭ��
    return i;
}
