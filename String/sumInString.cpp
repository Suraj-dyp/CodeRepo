/**************Problem*********************
 Find Sum of Patches of Numbers present in a String
 eg.. Input: "12a13bb14"
      Output: 12+13+14=39
 ****************************************/
/**********************Algorithm***********
 ->Starting From start Index Check for Digit or letter
 ->If Digit 
        add it to temporary string
   Else
        Convert temporary string to Integer using atoi()
        add this Integer to sum
        Empty temporary string
 ->If String Ends with Digits then the temporary string contaning no is not in sum
        add this temporary string to sum after converting it into Integer
        ***********************************/
#include<iostream>
#include<cctype>
#include<stdlib.h>
#include<string>
using namespace std;

int sum(string str)
{
  string tmp_str;
  int sum=0;
  
  //Extract Nos 
  for(int i=0;i<str.size();i++)
   {
	if(isdigit(str[i]))//if digit store it in temp string to handle more than one consecutive digits in string
        {
		tmp_str+=str[i];
        }
	else//if letter is found
	{
		sum+=atoi(tmp_str.c_str());
                tmp_str="";
	}
        
   }
   sum+=atoi(tmp_str.c_str());
   return sum;
}
int main()
{
  string str;
  cout << "\nEnter string: ";
  cin >> str;
  cout << sum(str) << endl;
}
