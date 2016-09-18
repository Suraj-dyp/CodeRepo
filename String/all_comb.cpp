/***************Porblem*********************
  1)Find All Continuous Substrings
  2)Find All Odd Length Contiguous substrings 
  3)Find All Even Length Contiguous substrings
*********************************************/
/****************Algorithm******************
1)First loop---Start Index
  Second loop---Length of Substring

2)First loop---Start Index
  Second loop---End Index

3)First loop---Length of Substring
  Second loop---Start Index
*************************************/
#include<iostream>
#include<string>
using namespace std;

void subStrings(string str)
{
  int len=str.length();
  for(int i=0;i<len;i++)//Start Index
    {
      for(int j=i;j<len;j++)//End Index
	{
	  cout << str.substr(i,j-i+1)<< endl;
	}
    }
}
void subStringsEvenLength(string str)
{
  int len=str.length();
  for(int i=0;i<len;i++)//Start Index
    {
      for(int j=i+1;j<len;j+=2)//End Index
	{
	  cout << str.substr(i,j-i+1)<< endl;
	}
    }
}
void subStringsOddLength(string str)
{
  int len=str.length();
  for(int i=0;i<len;i++)//Start Index
    {
      for(int j=i;j<len;j+=2)//End Index
	{
	  cout << str.substr(i,j-i+1)<< endl;
	}
    }
}
int main()
{
  string str;
  cout <<"Enter String: ";
  cin >> str;
  cout << "All Substrings---\n";
  subStrings(str);
  cout << "All Even Length Substrings---\n";
  subStringsEvenLength(str);
  cout << "All Odd Length Substrings---\n";
  subStringsOddLength(str);
}
