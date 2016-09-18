/**********Problem**********************
Find Largest-Sum of all substrings of a string
********************************/
/************Algorithm***************
1)Better Approach--(Try this.. Its very Easy)
   ->For Even Length String => return sum of string
   ->For Odd Length Dtring => return MAX(sum(substring excluding 1st element),
                                         sum(substring excluding last element))
2)Naive Approach--(This Program Implements this)
Find All Substring and their sum => return max of all
*************************************/
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

void longestSum_EvenLength_SubString()
{
  int len=str.length();
  int maxlen=0;
  for(int i=0;i<len;i++)//Start Index
    {
      for(int j=i+1;j<len;j+=2)//End Index
	{
	  int temp_len=sumSubstring_Recursive1(i,j);
	  if(maxlen<temp_len)
	    maxlen=temp_len;
	}
    }
  cout << "\nLongest-Sum Substring having Even-Length: " << maxlen << endl;
}

int main()
{
  cout << "Enter String: ";
  cin >> str;
  longestSum_EvenLength_SubString();
}
