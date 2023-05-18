#include <iostream>
#include <cstdlib>
#include <string>
using namespace std;

int calculatePower(int, int);

int main(int argc, char* argv[])
{
    int base, powerRaised, result;

    int a = std::stoi(argv[1]);
    int b = std::stoi(argv[2]);
    result = calculatePower(a, b);
    std::cout  << result;

    return 0;
}

int calculatePower(int base, int powerRaised)
{
    if (powerRaised != 0)
        return (base*calculatePower(base, powerRaised-1));
    else
        return 1;
}