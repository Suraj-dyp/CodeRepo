/*****************************Problem****************
Find sum of all digits in numbers from 1 to n
Input: n = 5
Output: Sum of digits in numbers from 1 to 5 = 15

Input: n = 12
Output: Sum of digits in numbers from 1 to 12 = 51

Input: n = 328
Output: Sum of digits in numbers from 1 to 328 = 3241

*****************************************************/
/********************ALgorithm****************************************************************************************

-->Find no of digits in n and subtract it from 1 which is equal to d.
       eg..  For 328, d is 2.
-->Calculate sum of digits from 1 to 10^d -1 using below formula   (i.e.a[d]) 
     sum(10^d - 1) = sum(10^(d-1) - 1) * 10 + 45*(10^(d-1)) where d=1,2,3........
       eg..  we compute sum of digits from 1 to 99 
-->Calculate MSD in n
       eg..   For 328, msd is 3
-->Overall Sum is Sum of digits from:
    a)1 to (msd*10^d) -1             =a[d]*msd + (msd*(msd-1)/2)*10^d
         eg..   For 328, sum of digits from 1 to 299 = sum(99) + sum of digits from 100 to 199 + sum of digits from 200 to 299
                                                     = sum(99) + {sum(99) + 1*100} + {sum(99) + 2*100}
                                                     = 3*sum(99) + (1+2)*100
                      
    b)msd*10^d to n                  = msd * (n % 10^d + 1) + sum(n % 10^d)
         eg..   For 328, sum of digits from 300 to 328 = 3*29 + sum(28)

***************************************************************************************************************/
#include<iostream>
#include<math.h>

using namespace std;

int sum(int n)
{
  //Base Case
  if(n<10)
    return n*(n+1)/2;
  //Other Case
  int d=log10(n);  //Find no of digits-1
  int *a=new int[d+1]; //a[1] is sum from (1 to 10^1 -1) and a[2] is sum from 1 to (10^2 -1)
  a[0]=0,a[1]=45;
  for(int i=2;i<=d;i++)//Compute sum of digits from 1 to (10^d -1)
    a[2]=a[i-1]*10+45*pow(10,i-1);

  int t=pow(10,d);
  int msd=n/t; //Find MSD in n

  return msd*a[d] + (msd*(msd-1)/2)*t //Sum of digits from 1 to "msd*10^d -1"
    + msd*(n%t +1) + sum(n%t);//Sum of digits from msd*10^d to n
  
}
int main()
{
  int n;
  cout << "Enter the n: ";
  cin >> n;
  cout << sum(n) << endl;
}


