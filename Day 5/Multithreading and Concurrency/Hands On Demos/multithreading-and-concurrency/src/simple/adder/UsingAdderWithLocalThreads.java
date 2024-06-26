package simple.adder;

public class UsingAdderWithLocalThreads {

	public static void main(String[] args) {
		String[] inFiles = { "./file1.txt", "./file2.txt" };
		String[] outFiles = { "./file1.out.txt", "./file2.out.txt" };

		for (int i = 0; i < inFiles.length; i++) {
//			AdderWithRunnableInterface adder = new AdderWithRunnableInterface(inFiles[i], outFiles[i]);
			AdderWithThreadClass adder = new AdderWithThreadClass(inFiles[i], outFiles[i]);
//			Thread thread = new Thread(adder);
//			thread.start();
			adder.start();

		}

	}

}
