#include <iostream>
#include <cstdlib>
#include <string>
using namespace std;

int fib(int);

int main(int argc, char* argv[]){
   int x , i=0;
   int n = std::stoi(argv[1]);
   while(i < n) {
      std::cout << fib(i) << " ";
      i++;
   }
   return 0;
}

int fib(int x) {
   if((x==1)||(x==0)) {
      return(x);
   }else {
      return(fib(x-1)+fib(x-2));
   }
}