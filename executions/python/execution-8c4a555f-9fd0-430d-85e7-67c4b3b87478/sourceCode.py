import sys

def power(base, exp):
    if (exp == 1):
        return (base)
    if (exp != 1):
        return (base * power(base, exp - 1))
base = int(sys.argv[1])
exp = int(sys.argv[2])
print(power(base, exp))
