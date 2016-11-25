/*WAP to find x^n using Divide & Conquer*/

/**************Analysis*********************
 Time Complexity = O(n)
 Space Complexity = O(1)
 *******************************************/

 #include<iostream>
using namespace std;

int main()
{
int x,n,res=1;
cout << "Enter the value of x and n\n";
cin >> x >> n;
for(int i=1;i<=n;i++)
    res=res*x;
cout << "value of x^n is: " << res << endl;
}