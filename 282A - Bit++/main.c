#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main()
{
    int n;
    scanf("%d", &n);

    char words[n][4];
    int ans = 0;
    for (int i = 0; i < n; i++)
    {
        scanf("%3s", words[i]);
        if (words[i][1] == '-')
        {
            ans--;
        }
        if (words[i][1] == '+')
        {
            ans++;
        }
    }

    printf("%d", ans);

    return 0;
}