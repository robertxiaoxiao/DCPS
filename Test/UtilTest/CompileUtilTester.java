package UtilTest;

import Util.CompilerUtil;

public class CompileUtilTester {


    public static void main(String[] args) throws InterruptedException {
        String loop = "public class HelloWorld {\n" +
                "    public static void main(String[] args) {\n" +
                "        while(true){\n" +
                //"            System.out.println(\"Hello World!\");\n" +
                "        }\n" +
                "       \n" +
                "    }\n" +
                "}";

        String sleep_loop = "public class sleepWorld {\n" +
                "    public static void main(String[] args) {\n" +
                "    try {\n" +
                "            Thread.sleep(6000);\n" +
                "        } catch (InterruptedException e) {\n" +
                "            e.printStackTrace();\n" +
                "        }\n" +
                "       System.out.println(\"Hello World!\");\n" +
                "        while(true){\n" +
                //"            System.out.println(\"Hello World!\");\n" +
                "        }\n" +
                "    }\n" +
                "}";

        String ok = "public class okWorld {\n" +
                "    public static void main(String[] args) {\n" +
                "       System.out.println(\"Hello World!\");\n" +
                "    }\n" +
                "}";

        long timeOut = 3000;
//        TestRun t = new TestRun(ok, "thread:ok", timeOut);
//        t.start();
//
        TestRun t1 = new TestRun(loop, "thread:loop:", timeOut);
        t1.start();

//        TestRun t2 = new TestRun(sleep_loop, "thread:sleep_loop:", timeOut);
//        t2.start();
//        t.join();
        t1.join();
        //     t2.join();

//        ThreadGroup currentGroup =
//                Thread.currentThread().getThreadGroup();
//        int noThreads = currentGroup.activeCount();
//        Thread[] lstThreads = new Thread[noThreads];
//        currentGroup.enumerate(lstThreads);
//        for (Thread cur: lstThreads  )
//            cur.stop();
        CompilerUtil.shutdown();
        System.out.println("ends");
        Thread.currentThread().stop();

    }

    static class TestRun extends Thread {
        String code;
        long timeout;

        TestRun(String code, String name, long time) {
            this.code = code;
            super.setName(name);
            timeout = time;
        }

        @Override
        public void run() {
            System.out.println(CompilerUtil.getRunInfo(code).toString());
        }
    }


}
