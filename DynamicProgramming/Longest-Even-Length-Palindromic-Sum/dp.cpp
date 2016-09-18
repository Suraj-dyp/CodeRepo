/**********Problem**********************
Find Length of Longest Even-Length Palindromic-Sum Substring of a String.
********************************/

/*Optimal SubStructure--If sum is counted for i=0,1,2.... and j=0,1,2.... respectively this could be used to get sum for i=0 and j=1,2,3..... (all possible<i,j> ),i=1 and j=2,3,4.....(all possible <i,j>) and so on.
      
                               or

sum of substring from i=start index to j=end index can be sum of substring from i to k and sub of substring from k+1 to j where k is any index between i to j(i inclusive and j exclusive)

       [Its Suitable to take mid index as k]

sum[i,j]=sum[i,k]+sum[k+1,j]
*/

/************************Algorithm***************
     Dynamic Programming(Bottom up approach) beacuse i could easily find out sum of substring from i=0 to j=0, i=1 to j=1 and so on(All are of length 1).

->Find sum of digits of all substrings of length 1(Diagnoal in below matrix)
->Find sum of digits of all substrings of length 2(Parallel line to above Diagnol) 
->So on for length 3,4,.............

                               
_____________________________________
|     |     |      |      |    |    |
|_____|_____|______|______|____|____|
|  \  |     |      |      |    |    |
|_____|_____|______|______|____|____|
|  \  |  \  |      |      |    |    |
|_____|_____|______|______|____|____|
|  \  |  \  |  \   |      |    |    |
|_____|_____|______|______|____|____|
|  \  |  \  |  \   |  \   |    |    |
|_____|_____|______|______|____|____|
|  \  |  \  |  \   |  \   |  \ |    |
|_____|_____|______|______|____|____|
********************************************************/

#include<iostream>
#include<string>

using namespace std;

int maxlength(string str)
{
  int length=str.length();
  int sum[length][length];
  int maxlen=0;
  
  //string of length 1(i and j are same so we need one loop only )
  for(int i=0;i<length;i++)
    sum[i][i]=str[i]-'0';

  //string of length 2 and more
  for(int len=2;len<=length;len++)
    {
      //Find i=start index and j= end index(No need of extra loop for j as we will reuse previous smaller solutions)
      for(int i=0;i<=length-len;i++)
	{
	  int j=i+len-1;
	  int mid_length=(j-i+1)/2; //mid length
	  int k=j-mid_length; //mid index
	  sum[i][j]=sum[i][k]+sum[k+1][j];

	  if(sum[i][k]==sum[k+1][j]//Palindromic Sum
	     && len%2==0  //Even Length
	     && maxlen<len)
	    { maxlen=len;}
	  
	}
    }
  return maxlen;
}
int main()
{
  string str;
  cout << "Enter String: ";
  cin >> str;
  cout << "Length of Longest Even-Length Palindroic-Sum: "<< maxlength(str) << endl;
}
