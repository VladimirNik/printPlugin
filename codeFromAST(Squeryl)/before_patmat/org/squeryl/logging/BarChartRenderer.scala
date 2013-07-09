package org.squeryl.logging {
  import scala.xml.Unparsed;
  import java.io.{FileOutputStream, PrintStream};
  import org.squeryl.InternalFieldMapper._;
  object BarChartRenderer extends scala.AnyRef {
    def <init>(): org.squeryl.logging.BarChartRenderer.type = {
      BarChartRenderer.super.<init>();
      ()
    };
    class Stat extends scala.AnyRef {
      <paramaccessor> private[this] val title: String = _;
      <stable> <accessor> <paramaccessor> def title: String = Stat.this.title;
      <paramaccessor> private[this] val xAxisLabel: String = _;
      <stable> <accessor> <paramaccessor> def xAxisLabel: String = Stat.this.xAxisLabel;
      <paramaccessor> private[this] val lines: Iterable[org.squeryl.logging.StatLine] = _;
      <stable> <accessor> <paramaccessor> def lines: Iterable[org.squeryl.logging.StatLine] = Stat.this.lines;
      <paramaccessor> private[this] val measureFromLike: org.squeryl.logging.StatLine => String = _;
      def <init>(title: String, xAxisLabel: String, lines: Iterable[org.squeryl.logging.StatLine], measureFromLike: org.squeryl.logging.StatLine => String): org.squeryl.logging.BarChartRenderer.Stat = {
        Stat.super.<init>();
        ()
      };
      def queryLabelsJSArray: String = Stat.this.lines.map[String, Iterable[String]](((sl: org.squeryl.logging.StatLine) => "\'".+(sl.statement.definitionOrCallSite).+("\'")))(collection.this.Iterable.canBuildFrom[String]).mkString("[", ",", "]");
      def measuresJSArray: String = Stat.this.lines.map[String, Iterable[String]](((x$1: org.squeryl.logging.StatLine) => Stat.this.measureFromLike.apply(x$1)))(collection.this.Iterable.canBuildFrom[String]).mkString("[", ",", "]")
    };
    def generateStatSummary(staticHtmlFile: java.io.File, n: Int): Unit = {
      val page: scala.xml.Elem = BarChartRenderer.page(new BarChartRenderer.this.Stat("Top ".+(n).+(" statements with longest avg"), "avg time", org.squeryl.InternalFieldMapper.queryToIterable[org.squeryl.logging.StatLine](StatsSchema.topRankingStatements(n, Measure.AvgExecTime)), ((sl: org.squeryl.logging.StatLine) => sl.avgExecTime.toString())), new BarChartRenderer.this.Stat("Top ".+(n).+(" most called statements"), "invocation count", org.squeryl.InternalFieldMapper.queryToIterable[org.squeryl.logging.StatLine](StatsSchema.topRankingStatements(n, Measure.InvocationCount)), ((sl: org.squeryl.logging.StatLine) => sl.invocationCount.toString())), new BarChartRenderer.this.Stat("Top ".+(n).+(" statements incurring most cummulative execution time"), "cummulative execution time", org.squeryl.InternalFieldMapper.queryToIterable[org.squeryl.logging.StatLine](StatsSchema.topRankingStatements(n, Measure.CumulativeExecutionTime)), ((sl: org.squeryl.logging.StatLine) => sl.cumulativeExecutionTime.toString())), new BarChartRenderer.this.Stat("Top ".+(n).+(" statements with highest avg row count"), "avg row count", org.squeryl.InternalFieldMapper.queryToIterable[org.squeryl.logging.StatLine](StatsSchema.topRankingStatements(n, Measure.AvgResultSetSize)), ((sl: org.squeryl.logging.StatLine) => sl.avgRowCount.toString())));
      val ps: java.io.PrintStream = new java.io.PrintStream(new java.io.FileOutputStream(staticHtmlFile));
      ps.print(page);
      ps.close()
    };
    private[this] val drawFunc: String = "\r\n    function drawBarGraph(divId, chartTitle, statType, queryClasses, measure) {\r\n              var data = new google.visualization.DataTable();\r\n\r\n              data.addColumn(\'string\', \'Z\');\r\n              data.addColumn(\'number\', statType);\r\n\r\n              data.addRows(queryClasses.length);\r\n\r\n              for (var j = 0; j < queryClasses.length; ++j) {\r\n                data.setValue(j, 0, queryClasses[j].toString());\r\n                data.setValue(j, 1, measure[j]);\r\n              }\r\n\r\n              var v = new google.visualization.BarChart(document.getElementById(divId))\r\n\r\n              v.draw(data,\r\n                       {title: chartTitle,\r\n                        width:600, height:400,\r\n                        vAxis: {title: \"Queries\"},\r\n                        hAxis: {title: statType}\r\n                       }\r\n                  );\r\n    }\r\n  ";
    <stable> <accessor> def drawFunc: String = BarChartRenderer.this.drawFunc;
    def funcCalls(stats: Seq[org.squeryl.logging.BarChartRenderer.Stat]): String = {
      val sb: StringBuffer = new java.this.lang.StringBuffer();
      var i: Int = 0;
      stats.foreach[StringBuffer](((s: org.squeryl.logging.BarChartRenderer.Stat) => {
        i = i.+(1);
        sb.append("drawBarGraph(\'chart");
        sb.append(i);
        sb.append("\',\'");
        sb.append(s.title);
        sb.append("\',\'");
        sb.append(s.xAxisLabel);
        sb.append("\',");
        sb.append(s.queryLabelsJSArray);
        sb.append(",");
        sb.append(s.measuresJSArray);
        sb.append(");\n")
      }));
      sb.toString()
    };
    def page(stats: org.squeryl.logging.BarChartRenderer.Stat*): scala.xml.Elem = {
      var $tmpscope: scala.xml.NamespaceBinding = scala.this.Predef.$scope;
      $tmpscope = new scala.xml.NamespaceBinding(null, "http://www.w3.org/1999/xhtml", $tmpscope);
      {
        val $scope: scala.xml.NamespaceBinding = $tmpscope;
        new scala.xml.Elem(null, "html", scala.xml.Null, $scope, false, ({
          val $buf: scala.xml.NodeBuffer = new scala.xml.NodeBuffer();
          $buf.&+(new scala.xml.Text("\n      "));
          $buf.&+({
            {
              new scala.xml.Elem(null, "head", scala.xml.Null, $scope, false, ({
                val $buf: scala.xml.NodeBuffer = new scala.xml.NodeBuffer();
                $buf.&+(new scala.xml.Text("\n        "));
                $buf.&+({
                  {
                    var $md: scala.xml.MetaData = scala.xml.Null;
                    $md = new scala.xml.UnprefixedAttribute("content", new scala.xml.Text("text/html; charset=utf-8"), $md);
                    $md = new scala.xml.UnprefixedAttribute("http-equiv", new scala.xml.Text("content-type"), $md);
                    new scala.xml.Elem(null, "meta", $md, $scope, true)
                  }
                });
                $buf.&+(new scala.xml.Text("\n        "));
                $buf.&+({
                  {
                    new scala.xml.Elem(null, "title", scala.xml.Null, $scope, false, ({
                      val $buf: scala.xml.NodeBuffer = new scala.xml.NodeBuffer();
                      $buf.&+(new scala.xml.Text("Performance profile of Squeryl queries"));
                      $buf
                    }: _*))
                  }
                });
                $buf.&+(new scala.xml.Text("\n        "));
                $buf.&+({
                  {
                    var $md: scala.xml.MetaData = scala.xml.Null;
                    $md = new scala.xml.UnprefixedAttribute("src", new scala.xml.Text("http://www.google.com/jsapi"), $md);
                    $md = new scala.xml.UnprefixedAttribute("type", new scala.xml.Text("text/javascript"), $md);
                    new scala.xml.Elem(null, "script", $md, $scope, false)
                  }
                });
                $buf.&+(new scala.xml.Text("\n        "));
                $buf.&+({
                  {
                    var $md: scala.xml.MetaData = scala.xml.Null;
                    $md = new scala.xml.UnprefixedAttribute("type", new scala.xml.Text("text/javascript"), $md);
                    new scala.xml.Elem(null, "script", $md, $scope, false, ({
                      val $buf: scala.xml.NodeBuffer = new scala.xml.NodeBuffer();
                      $buf.&+(new scala.xml.Text("\n          google.load(\'visualization\', \'1\', {packages: [\'corechart\']});\n        "));
                      $buf
                    }: _*))
                  }
                });
                $buf.&+(new scala.xml.Text("\n        "));
                $buf.&+({
                  {
                    var $md: scala.xml.MetaData = scala.xml.Null;
                    $md = new scala.xml.UnprefixedAttribute("type", new scala.xml.Text("text/javascript"), $md);
                    new scala.xml.Elem(null, "script", $md, $scope, false, ({
                      val $buf: scala.xml.NodeBuffer = new scala.xml.NodeBuffer();
                      $buf.&+(new scala.xml.Text("\n\n          "));
                      $buf.&+(scala.xml.Unparsed.apply(BarChartRenderer.this.drawFunc));
                      $buf.&+(new scala.xml.Text("\n\n          function drawVisualization() {\n            "));
                      $buf.&+(scala.xml.Unparsed.apply(BarChartRenderer.this.funcCalls(stats)));
                      $buf.&+(new scala.xml.Text("\n          }\n\n          google.setOnLoadCallback(drawVisualization);\n        "));
                      $buf
                    }: _*))
                  }
                });
                $buf.&+(new scala.xml.Text("\n      "));
                $buf
              }: _*))
            }
          });
          $buf.&+(new scala.xml.Text("\n      "));
          $buf.&+({
            {
              var $md: scala.xml.MetaData = scala.xml.Null;
              $md = new scala.xml.UnprefixedAttribute("style", new scala.xml.Text("font-family: Arial;border: 0 none;"), $md);
              new scala.xml.Elem(null, "body", $md, $scope, false, ({
                val $buf: scala.xml.NodeBuffer = new scala.xml.NodeBuffer();
                $buf.&+(new scala.xml.Text("\n        "));
                $buf.&+({
                  {
                    var $md: scala.xml.MetaData = scala.xml.Null;
                    $md = new scala.xml.UnprefixedAttribute("style", new scala.xml.Text("width: 1000px; height: 400px;"), $md);
                    $md = new scala.xml.UnprefixedAttribute("id", new scala.xml.Text("chart1"), $md);
                    new scala.xml.Elem(null, "div", $md, $scope, false)
                  }
                });
                $buf.&+(new scala.xml.Text("\n        "));
                $buf.&+({
                  {
                    var $md: scala.xml.MetaData = scala.xml.Null;
                    $md = new scala.xml.UnprefixedAttribute("style", new scala.xml.Text("width: 1000px; height: 400px;"), $md);
                    $md = new scala.xml.UnprefixedAttribute("id", new scala.xml.Text("chart2"), $md);
                    new scala.xml.Elem(null, "div", $md, $scope, false)
                  }
                });
                $buf.&+(new scala.xml.Text("\n        "));
                $buf.&+({
                  {
                    var $md: scala.xml.MetaData = scala.xml.Null;
                    $md = new scala.xml.UnprefixedAttribute("style", new scala.xml.Text("width: 1000px; height: 400px;"), $md);
                    $md = new scala.xml.UnprefixedAttribute("id", new scala.xml.Text("chart3"), $md);
                    new scala.xml.Elem(null, "div", $md, $scope, false)
                  }
                });
                $buf.&+(new scala.xml.Text("\n        "));
                $buf.&+({
                  {
                    var $md: scala.xml.MetaData = scala.xml.Null;
                    $md = new scala.xml.UnprefixedAttribute("style", new scala.xml.Text("width: 1000px; height: 400px;"), $md);
                    $md = new scala.xml.UnprefixedAttribute("id", new scala.xml.Text("chart4"), $md);
                    new scala.xml.Elem(null, "div", $md, $scope, false)
                  }
                });
                $buf.&+(new scala.xml.Text("\n      "));
                $buf
              }: _*))
            }
          });
          $buf.&+(new scala.xml.Text("\n    "));
          $buf
        }: _*))
      }
    }
  }
}