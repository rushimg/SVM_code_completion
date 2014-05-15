public DoubleMatrix2D sort(DoubleMatrix2D matrix, hep.aida.tdouble.bin.DoubleBinFunction1D aggregate) {
        // precompute aggregates over rows, as defined by "aggregate"

        // a bit clumsy, because Statistic.aggregate(...) is defined on columns,
        // so we need to transpose views
        DoubleMatrix2D tmp = matrix.like(1, matrix.rows());
        hep.aida.tdouble.bin.DoubleBinFunction1D[] func = { aggregate };
        DoubleStatistic.aggregate(matrix.viewDice(), func, tmp);
        double[] aggr = tmp.viewRow(0).toArray();
        return sort(matrix, aggr);
    }
