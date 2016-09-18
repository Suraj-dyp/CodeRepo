/***************Problem********************
Find Longest Even-Length Palindromic-Sum Substring
******************************************/
/**************Algorithm********************
   -->Find all Possible Even Length Substring 
   -->For Each SubString Check for its Palindromic-Sum
   -->Return Max-Length Substring of all Substrings satisfying Palindromic-Sum
   ******************************************/

#include<iostream>
#include<string>

using namespace std;

int longest_EvenLength_PalindromicSum_Substring(string str)
{
  int len_str=str.size();
  int maxlen=0;
  for(int i=0;i<len_str;i++)//Start Index
    {
      for(int j=i+1;j<len_str;j+=2)//End Index
	{
	  int len_sub_str=j-i+1;
	  int div_into_half=len_sub_str/2;
	  int sum_left_half=0,sum_right_half=0;
	  
	  for(int k=0;k<div_into_half;k++)//Sum of Left and Right half
	    {
	      sum_left_half+=(str[i+k]-'0');
	      sum_right_half+=(str[i+div_into_half+k]-'0');
	    }

	  if(sum_left_half==sum_right_half && maxlen<len_sub_str)
	    maxlen=len_sub_str;
	}
    }
  return maxlen;

}


int main()
{
  string str;
  cout << "Enter string: ";
  cin >> str;
  cout << "\nMax Length of Even Length Substring with Equal Sum In Both Half is : " << longest_EvenLength_PalindromicSum_Substring(str) << endl; 
}
