package dna.projects.examples;

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

public class Example1 {
	public static void main(String[] args) throws IOException,
			DiffNotApplicableException, AggregationException,
			InterruptedException {
		GraphGenerator gg = new RandomGraph(100, 1000, true);
		DiffGenerator dg = new RandomDiff(500, 0, true);

		Metric dd = new DegreeDistribution();
		Metric cc1 = new OtcComp();
		Metric cc2 = new OtcIncrByDiff();
		Metric cc3 = new OtcIncrByEdge();
		Metric[] metrics = new Metric[] { dd, cc1, cc2, cc3 };

		Series s = new Series(gg, dg, metrics, "data/example1/");
		SeriesData data = s.generate(3, 5);

		Plotting.plotRun(data, data.getRun(0), "plots/example1/");
		Plotting.plotDistributions(data, data.getRun(0),
				"plots/example1-distributions/");
	}
}
