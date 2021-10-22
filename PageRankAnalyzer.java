package mathcomp.oletsky.hirsh;


import mathcomp.oletsky.hierarchyanalysis.SaatiAnalyzer;
import mathcomp.oletsky.mathhelper.VectMatr;
import mathcomp.oletsky.pagerank.MarkovWalking;

import java.util.Arrays;

public class PageRankAnalyzer {
    public static void main(String[] args) {
        double beta = 0.85;
        int[][] papers = {
                {0},
                {1, 8},
                {2, 3, 4},
                {5, 6, 7}
        };

        int[][] navGraph = {
                {0, 1, 0, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 1, 1, 1, 1, 1, 0},
                {1, 0, 1, 0, 1, 1, 1, 1, 0},
                {1, 0, 1, 1, 0, 1, 1, 1, 0},
                {1, 0, 1, 1, 1, 0, 1, 1, 1},
                {1, 0, 1, 1, 1, 1, 0, 1, 1},
                {1, 0, 1, 1, 1, 1, 1, 0, 0},
                {1, 1, 0, 0, 0, 0, 0, 0, 0}


        };
        int kolAuthors = papers.length;
        System.out.println("Amount of authors = " + kolAuthors);
        int kol = navGraph.length;
        System.out.println("Total amount of papers - " + kol);
        System.out.println("Papers by authors:");
        for (int i = 0; i < kolAuthors; i++) {
            System.out.println("Author " + i + " - " + papers[i].length);
        }
        System.out.println("Counts of incoming links:");
        for (int i = 0; i < kol; i++) {
            int count = LinkAnalyzer.countIncomingLinks(navGraph, i);
            System.out.println(i + " - " + count);
        }
        double[][] transMatr = MarkovWalking.obtainTransMatrFromGraph(
                navGraph, beta
        );
        double[] ranks = MarkovWalking.getStationaryDistribution(transMatr);
        System.out.println("Node ranks:");
        VectMatr.defaultOutputVector(ranks);
        System.out.println("Hirshes:");
        for (int i = 0; i < kolAuthors; i++) {
            int h = LinkAnalyzer.calculateHirsh(i, papers, navGraph);
            System.out.println(i + " - " + h);
        }
//Saati

        double[][] compar = {
                {1., 2., 2., 3.},
                {1. / 2., 1., 1., 2.},
                {1. / 2., 1., 1., 2.},
                {1. / 3, 1. / 2., 1. / 2., 1.}
        };

        double[] vv = SaatiAnalyzer.getSaatiVector(compar);
        System.out.println("Expert Saati vector");
        VectMatr.defaultOutputVector(vv);
        int[][] cTau = {
                {1, 1, 2},
                {0, 1},
                {1}
        };
        double tau=1.1;
        double[] vvTau=SaatiAnalyzer.calculateSaatiVectorByIntPreferences(
                cTau,
                tau
        );
        System.out.println("Relaxed vector:");
        VectMatr.defaultOutputVector(vvTau);


    }


}

