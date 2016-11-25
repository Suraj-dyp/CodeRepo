/*WAP to find x^n using Divide & Conquer*/

/*************Solution**********************
 Divide x.x..   ....n times into two equal parts
 Conquer both parts independently
 Combine both parts
 *******************************************/

/**************Analysis*********************
 Time Complexity = O(n)
 Space Complexity = O(1)
 *******************************************/

#include<iostream>
using namespace std;

int power(int x, int n)
{
  //Divide
  int m = n/2,res1=1,res2=1;
  //Conquer Base Case
  if(n==1)
  {
      return x;
  }
  //Conquer Recursive Definition
  res1 = power(x,m);
  if(n%2==0)
    res2 = power(x,m);
  else
    res2 = power(x,m+1);
  //Combine 
  return (res1 * res2); 
}
int main()
{
  int x,n;
  cout << "Enter value of x and n\n";
  cin >> x >> n;
  cout << "x^n is " << power(x,n) << endl;
}