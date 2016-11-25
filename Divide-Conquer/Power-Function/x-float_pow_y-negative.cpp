/*WAP to find x^n using Divide & Conquer*/

/*************Solution**********************
 Divide x.x..   ....n times into two equal parts
 Conquer both parts independently
 Combine both parts
 *******************************************/

/**************Analysis*********************
 Time Complexity = O(log n)
 Space Complexity = O(1)
 *******************************************/

#include<iostream>
#include<cmath>
using namespace std;

float power(float x, int n)
{
  //Divide
  int m = n/2,n1 = abs(n);
  float res1=1,res2;
  //Conquer Base Case
  if(n1==1)
  {
      return x;
  }
  //Conquer Recursive Definition
  res1 = power(x,m);
  if(n1%2==0)
    res2 = res1;
  else
    res2 = x*res1;
  //Combine
  if(n<0)
    return 1.0/(res1 * res2);
  else 
      return (res1*res2);
}
int main()
{
  int n;float x;
  cout << "Enter value of x and n\n";
  cin >> x >> n;
  cout << "x^n is " << power(x,n) << endl;
}
