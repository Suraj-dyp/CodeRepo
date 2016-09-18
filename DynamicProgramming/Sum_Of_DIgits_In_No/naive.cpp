/******Problem****************
Find sum of all digits in numbers from 1 to n
Input: n = 5
Output: Sum of digits in numbers from 1 to 5 = 15

Input: n = 12
Output: Sum of digits in numbers from 1 to 12 = 51

Input: n = 328
Output: Sum of digits in numbers from 1 to 328 = 3241

*****************************/
#include<iostream>

using namespace std;

int sum(int n)
{
  int sum=0;
  for(int i=1;i<=n;i++)
    {
      int num=i;
      while(num!=0)
	{
	  sum+=(num%10);
	  num/=10;
	}
    }
  return sum;
}
int main()
{
  int n;
  cout << "Enter the n: ";
  cin >> n;
  cout << sum(n) << endl;
}
