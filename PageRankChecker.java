package mathcomp.oletsky.hirsh;

import mathcomp.oletsky.hierarchyanalysis.SaatiAnalyzer;
import mathcomp.oletsky.mathhelper.VectMatr;
import mathcomp.oletsky.pagerank.MarkovWalking;

import java.util.Arrays;

public class PageRankChecker {
    public static void main(String[] args) {
        double beta=0.85;
        int [][] navGraph={
                {0, 1, 1, 0, 0, 0, 0},
                {1, 0, 1, 1, 1, 0, 0},
                {1, 1, 0, 0, 0, 1, 1},
                {1, 1, 0, 0, 0, 0, 0},
                {1, 1, 0, 0, 0, 0, 0},
                {1, 0, 1, 0, 0, 0, 1},
                {1, 0, 1, 0, 0, 1, 0}

        };
        double[][] transMatr = MarkovWalking.obtainTransMatrFromGraph(
                navGraph, beta
        );
        double[][] transposed=VectMatr.transposeMatrix(transMatr);
        double[] ranks= MarkovWalking.getStationaryDistribution(transMatr);
        System.out.println("By Markov walking:");
        VectMatr.defaultOutputVector(ranks);
        System.out.println("By Saati vector:");
        double[] ranksSaati= SaatiAnalyzer.getSaatiVector(transposed);
        VectMatr.defaultOutputVector(ranksSaati);

    }


}

