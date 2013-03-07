package dna.projects.clusteringCoefficient;

import java.io.IOException;

import dna.diff.DiffNotApplicableException;
import dna.diff.generator.DiffGenerator;
import dna.diff.generator.RandomDiff;
import dna.graph.generator.GraphGenerator;
import dna.graph.generator.RandomGraph;
import dna.metrics.Metric;
import dna.metrics.degree.DegreeDistribution;
import dna.metrics.triangles.open.OtcComp;
import dna.metrics.triangles.open.OtcIncrByDiff;
import dna.metrics.triangles.open.OtcIncrByEdge;
import dna.plot.Plotting;
import dna.series.AggregationException;
import dna.series.Series;
import dna.series.data.SeriesData;
import dna.util.Log;
import dna.util.Rand;
import dna.util.Log.LogLevel;

public class ClusteringCoefficient {

	/**
	 * @param args
	 * @throws DiffNotApplicableException
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws AggregationException 
	 */
	public static void main(String[] args) throws IOException,
			DiffNotApplicableException, InterruptedException, AggregationException {
		Log.setLogLevel(LogLevel.debug);
		
		String seriesPath = "data/";
		String plotPath = "plots/";
		int runs = 1;
		int diffs = 10;
		
		Rand.init(0);

		GraphGenerator graph = new RandomGraph(1000, 10000, false);
		DiffGenerator diff = new RandomDiff(2000, 1000, false);

		Metric dd = new DegreeDistribution();
		Metric cc1 = new OtcComp();
		Metric cc2 = new OtcIncrByDiff();
		Metric cc3 = new OtcIncrByEdge();
		Metric[] metrics = new Metric[] { dd, cc1, cc3 };

		Series s = new Series(graph, diff, metrics, seriesPath);
		SeriesData sd = s.generate(runs, diffs);

		Plotting.plotRun(sd, sd.getAggregation(), plotPath + "aggr/");
		Plotting.plotRun(sd, sd.getRun(0), plotPath + "0/");
//		Plotting.plotRun(sd, sd.getRun(1), plotPath + "1/");
	}

}
