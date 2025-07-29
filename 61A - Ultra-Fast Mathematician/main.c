#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main()
{
    char a[101];
    char b[101];
    scanf("%s", a);
    scanf("%s", b);
    int n = strlen(a);
    char *ans = (char *)malloc(n + 1);
    for (int i = 0; a[i] != '\0'; i++)
    {
        if (a[i] == b[i])
        {
            ans[i] = '0';
        }
        else
        {
            ans[i] = '1';
        }
    }
    ans[n] = '\0';
    printf("%s", ans);
    return 0;
}