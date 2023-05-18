class Power {
    public static void main(String[] args) {
        int result = power( Integer.valueOf(args[0]),  Integer.valueOf(args[1]));
        System.out.println(result);
    }

    public static int power(int base, int powerRaised) {
        if (powerRaised != 0) {

            // recursive call to power()
            return (base * power(base, powerRaised - 1));
        }
        else {
            return 1;
        }
    }
}