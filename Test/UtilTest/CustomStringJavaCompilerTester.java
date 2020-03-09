package UtilTest;

import Util.CustomStringJavaCompiler;

public class CustomStringJavaCompilerTester {

    /**
     * to handle the source code in string pattern
     */
    public void testCode(String code) {
        CustomStringJavaCompiler compiler = new CustomStringJavaCompiler(code);
        boolean res = compiler.compiler();
        if (res) {
            System.out.println("编译成功");
            System.out.println("compilerTakeTime：" + compiler.getCompilerTakeTime());
            try {
                compiler.runMainMethod();
                System.out.println("runTakeTime：" + compiler.getRunTakeTime());
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(compiler.getRunResult());
            System.out.println("诊断信息：" + compiler.getCompilerMessage());
        } else {
            System.out.println("编译失败");
            System.out.println(compiler.getCompilerMessage());
        }
    }

    public static void main(String[] args) {
        CustomStringJavaCompilerTester tester = new CustomStringJavaCompilerTester();
//        String deadLoopCode = "public class HelloWorld {\n" +
//                "    public static void main(String []args) {\n" +
//                "\t\tfor(int i=0; i < 1; i++){\n" +
//                "\t\t\t       System.out.println(\"Hello World!\");\n" +
//                "\t\t}\n" +
//                "    }\n";
//        tester.testCode(deadLoopCode);

        String helloWorldCode = "package Util;\npublic class HelloWorld {\n" +
                "    public static void main(String []args) {\n" +
                "\t\t\t       System.out.println(\"Hello World!\");\n" +
                "\t\t}\n" +
                "}";

        tester.testCode(helloWorldCode);
    }

}
