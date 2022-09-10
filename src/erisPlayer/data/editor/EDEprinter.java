package erisPlayer.data.editor;

class EDEprinter {
	
	static void listMainActions() {
		System.out.println("\n ---- Eris-Data-Editor : ----");
		System.out.println(" _> 0 : Exit");
		System.out.println(" _> 1 : Add new Channel");
		System.out.println(" _> 2 : Edit existing Channel");
		System.out.println(" _> 3 : Remove existing Channel");
		System.out.println(" _> 4 : List all Channels");
		System.out.println(" _> 5 : List all Content");
	}
	
	static void listAddActions() {
		System.out.println("\n --- Add new Channel : ----");
		System.out.println(" _> 0 : Exit");
		System.out.println(" _> 1 : Add new Channel");
	}
	
	static void listEditActions() {
		System.out.println("\n ---- Edit Channel : ----");
		System.out.println(" _> 0 : Exit");
		System.out.println(" _> 1 : Edit existing Channel");
		System.out.println(" _> 2 : List all Channels");
		System.out.println(" _> 3 : Edit Videos");
	}
	
	static void listVideoActions() {
		System.out.println("\n ---- Edit Videos : ----");
		System.out.println(" _> 0 : Exit");
		System.out.println(" _> 1 : Edit existing Video");
		System.out.println(" _> 2 : List all Videos");
		System.out.println(" _> 3 : refresh Videos");
		System.out.println(" _> 4 : Remove Video");
	}
	
	static void listRemoveActions() {
		System.out.println("\n ---- Remove Channel : ----");
		System.out.println(" _> 0 : Exit");
		System.out.println(" _> 1 : Enter Channel-Number for deletion");
		System.out.println(" _> 2 : List all Channels");
	}
}
