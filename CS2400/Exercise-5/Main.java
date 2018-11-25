public class Main {

    public static void main(String[] args) {
        HashSetInterface a = new HashSetQP();
        a.add(new String("rat"));
        a.add(new String("abc"));
        a.add(new String("art"));
        a.add(new String("rat"));
        a.add(new String("tar"));

        System.out.println(a.contains("art"));
        System.out.println(a.contains("fart"));
        a.remove("art");
        System.out.println(a.contains("art"));
        System.out.println(a.size());



        a.clear();
        System.out.println(a.isEmpty());

        for(int i = 0; i < 157; i++) {
            a.add(new String("" + i));
        }
        Object[] b = a.toArray();
        for(int i = 0; i < b.length; i++) {
            System.out.println(b[i]);
        }

    }
}
