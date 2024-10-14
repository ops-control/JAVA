import java.util.Scanner;

class RLE {

	private static Scanner scanner = new Scanner(System.in);

	private final static char FLAG = '#';

	public static void main(String[] args) {
		
		System.out.println("Entrez les données à comprimer : ");
		String dta = scanner.nextLine();
		String rle = RLEAlgorithm.compress(dta, FLAG);
		System.out.println("Forme compressée:   " + rle + "\n[ratio = " + rle.length()*100.0/dta.length() + "%]");
		String dcp = "";
		try {
			dcp = RLEAlgorithm.decompresse(rle, FLAG);
		} catch (RLEException e) {
			e.printStackTrace();
		}
		if (!dcp.equals(dta)) {
			System.out.println("Erreur - données corrompues:" + dcp);
		} else {
			System.out.println("décompression ok!");
		}

		// teste la decompression
		System.out.println("Entrez les données à décompresser : ");
		dta = scanner.nextLine();
		try {
			dcp = RLEAlgorithm.decompresse(dta, FLAG);
			System.out.println("décompressé : " + dcp);
		}
		catch (RLEException exception) {
			System.out.println("Erreur de décompression : " + exception.getMessage() + "\n");
			System.out.println("décodé à ce stade : " + exception.getDecoded() + "\n");
			System.out.println("non décodé        : " + exception.getRemaining());
		}
	}
}

class RLEException extends Exception {
	// A COMPLETER
}

class RLEAlgorithm {

	public static String compress(String data, char flag) {
		// A COMPLETER
	}

	public static String decompresse(String rledata, char flag) throws RLEException	{
		// A COMPLETER
}
