public class Andrana {

	public static int find(int startingNode, int[] fromIds, int[] toIds)
	{
		if ((fromIds.length <=0)|| (toIds.length>10000)) {
			return 0;

		}
		boolean endFound = false ;
		boolean lapEnded = false ;
		boolean startSearch = true ;

		int node = startingNode;
		int indexNode = toIds[0];
		int oldIndex = toIds[0];

		while (!endFound && !lapEnded) {

			if (!startSearch && (node == startingNode))
			{
				lapEnded = true;
				indexNode = oldIndex;
				break;
			}
			if(startSearch)
			{
				startSearch = false;
			}

			if(exist(fromIds, node)!=-1)
			{
				oldIndex = indexNode;
				indexNode = exist(fromIds, node);
				node =  toIds[indexNode];
			}
			else
			{
				endFound = true;
			}
		}
		return node;

	}

	public static int exist (int[]ints , int k ) {

		for (int i=0;i<ints.length;i++) {
			if (ints[i]==k) {
				return i;
			}
		}

		return -1 ;

	}

	public static void main(String[] args) {

		int startNodeId = 2;
		int n = 7;

		int[] fromIds = new int[] {1,7,3,4,6,9,2,5};
		int[] toIds = new int[]   {3,3,4,6,9,5,6,13};

		for (int i = 0; i < n; i++)
		{
			System.out.println("from : " + fromIds[i] + " -> " + toIds[i]);
		}

		int endPointId = find(startNodeId, fromIds, toIds);
		System.out.println("L'ID du dernier noeud : "+endPointId);
	}

}
