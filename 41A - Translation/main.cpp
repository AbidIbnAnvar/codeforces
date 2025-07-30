#include <iostream>
#include <string>
using namespace std;

int main()
{
    string s1;
    cin >> s1;
    string s2;
    cin >> s2;
    if (s1.length() != s2.length())
    {
        cout << "NO";
        return 0;
    }
    int i = 0;
    int n = s1.length();
    while (i < n)
    {
        if (s1[i] != s2[n - i - 1])
        {
            cout << "NO";
            return 0;
        }
        i++;
    }
    cout << "YES";
    return 0;
}