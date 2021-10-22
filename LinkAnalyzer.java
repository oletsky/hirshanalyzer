package mathcomp.oletsky.hirsh;

import java.util.stream.IntStream;

public class LinkAnalyzer {
    public static int countIncomingLinks(int[][] graph, int node) {
        int m = graph.length;
        int n = graph[0].length;
        if (m!=n) throw new RuntimeException();
        int count=0;
        for (int i=0; i<m; i++) {
            if (graph[i][node]==1) count++;
        }
        return count;
    }

    public static int calculateHirsh(
            int author,
            int[][] papers,
            int[][] graph) {
        int[] authorPapers = papers[author];
        
        int hirsh=0;
        for (int i = 1; i <=authorPapers.length; i++) {
            final int h=i;
            long c = IntStream.of(authorPapers).
                    filter(v->countIncomingLinks(graph,v)>=h).
                    count();

            boolean flag=false;
            if (c<h) {
                hirsh=h-1;
                flag=true;
                break;
            }
            if (!flag) hirsh = authorPapers.length;
        }
        return hirsh;
    }
}
