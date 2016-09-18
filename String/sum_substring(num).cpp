/**************Problem*********************
Find Sum of Each Digits of a Substring of a string(Contains only Digits)
******************************************/
/*********************Algorithm*****************
1)Iterative---using atoi() of stdlib.h
2)Iterative---Converting ASCII Characters into Decimal
3)Recursie--- 
    len(i,j)=len(i,i)+len(i+1,j)
4)Recursive---
    len(i,j)=len(i,j-k)+len(j-k+1,j)   
           where k= (j-i+1)/2   i.e. Half of Length of Substring
5)Recursive--- k could be any index between i and j(i inclusive and j exclusive)
    len(i,j)=len(i,k)+len(k+1,j)
***********************************************/
#include<iostream>
#include<stdlib.h>
#include<string>

using namespace std;

string str;

int sumSubstring_Recursive1(int i, int j)
{
  int len=j-i+1;
  int mid=len/2;
  if(i==j)
    return str[i]-'0';
  return  sumSubstring_Recursive1(i,j-mid)+
    sumSubstring_Recursive1(j-mid+1,j);
}

int sumSubstring_Recursive2(int i, int j)
{
 
  if(i==j)
    return  (str[i]-'0');
  return sumSubstring_Recursive2(i,i) + sumSubstring_Recursive2(i+1,j);
}

int sumSubstring_Iterative(int i, int j)
{
  int sum=0;
  for(int k=i;k<=j;k++)
    {
      sum+=(str[k]-'0');
    }
  return sum;
}

int sumSubstring_Iterative1(int i, int j)
{
  int sum=0;
  int num=atoi(str.substr(i,j-i+1).c_str());
   while(num!=0)
    {
      sum+=(num%10);
      num=num/10;
    }
   return sum;
}

int main()
{
  int start,end;
  
  cout << "Enter String: ";
  cin >> str;
  cout << "Length of String is: " << str.size() << endl;
  cout << "Enter Start & End Index of Substring to find Sum: ";
  cin >> start >> end;
  cout << "Sum is: " << sumSubstring_Recursive1(start,end)<< endl;
  cout << "Sum is: " <<  sumSubstring_Recursive2(start,end)<< endl;
  cout << "Sum is: " << sumSubstring_Iterative(start,end)<< endl;
  cout << "Sum is: " << sumSubstring_Iterative1(start,end)<< endl;
}
