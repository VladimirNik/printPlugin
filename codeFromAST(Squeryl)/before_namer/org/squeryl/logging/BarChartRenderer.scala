package org.squeryl.logging {
  import xml.Unparsed;
  import java.io.{FileOutputStream, PrintStream};
  import org.squeryl.InternalFieldMapper._;
  object BarChartRenderer extends scala.AnyRef {
    def <init>() = {
      super.<init>();
      ()
    };
    class Stat extends scala.AnyRef {
      <paramaccessor> val title: String = _;
      <paramaccessor> val xAxisLabel: String = _;
      <paramaccessor> val lines: Iterable[StatLine] = _;
      <paramaccessor> private[this] val measureFromLike: _root_.scala.Function1[StatLine, String] = _;
      def <init>(title: String, xAxisLabel: String, lines: Iterable[StatLine], measureFromLike: _root_.scala.Function1[StatLine, String]) = {
        super.<init>();
        ()
      };
      def queryLabelsJSArray = lines.map(((sl) => "\'".$plus(sl.statement.definitionOrCallSite).$plus("\'"))).mkString("[", ",", "]");
      def measuresJSArray = lines.map(((x$1) => measureFromLike(x$1))).mkString("[", ",", "]")
    };
    def generateStatSummary(staticHtmlFile: java.io.File, n: Int) = {
      val page = BarChartRenderer.page(new Stat("Top ".$plus(n).$plus(" statements with longest avg"), "avg time", StatsSchema.topRankingStatements(n, Measure.AvgExecTime), ((sl) => sl.avgExecTime.toString)), new Stat("Top ".$plus(n).$plus(" most called statements"), "invocation count", StatsSchema.topRankingStatements(n, Measure.InvocationCount), ((sl) => sl.invocationCount.toString)), new Stat("Top ".$plus(n).$plus(" statements incurring most cummulative execution time"), "cummulative execution time", StatsSchema.topRankingStatements(n, Measure.CumulativeExecutionTime), ((sl) => sl.cumulativeExecutionTime.toString)), new Stat("Top ".$plus(n).$plus(" statements with highest avg row count"), "avg row count", StatsSchema.topRankingStatements(n, Measure.AvgResultSetSize), ((sl) => sl.avgRowCount.toString)));
      val ps = new PrintStream(new FileOutputStream(staticHtmlFile));
      ps.print(page);
      ps.close
    };
    val drawFunc = "\r\n    function drawBarGraph(divId, chartTitle, statType, queryClasses, measure) {\r\n              var data = new google.visualization.DataTable();\r\n\r\n              data.addColumn(\'string\', \'Z\');\r\n              data.addColumn(\'number\', statType);\r\n\r\n              data.addRows(queryClasses.length);\r\n\r\n              for (var j = 0; j < queryClasses.length; ++j) {\r\n                data.setValue(j, 0, queryClasses[j].toString());\r\n                data.setValue(j, 1, measure[j]);\r\n              }\r\n\r\n              var v = new google.visualization.BarChart(document.getElementById(divId))\r\n\r\n              v.draw(data,\r\n                       {title: chartTitle,\r\n                        width:600, height:400,\r\n                        vAxis: {title: \"Queries\"},\r\n                        hAxis: {title: statType}\r\n                       }\r\n                  );\r\n    }\r\n  ";
    def funcCalls(stats: Seq[Stat]) = {
      val sb = new StringBuffer();
      var i = 0;
      stats.foreach(((s) => {
        i.$plus$eq(1);
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
      sb.toString
    };
    def page(stats: _root_.scala.<repeated>[Stat]) = {
      var $tmpscope: _root_.scala.xml.NamespaceBinding = $scope;
      $tmpscope = new _root_.scala.xml.NamespaceBinding(null, "http://www.w3.org/1999/xhtml", $tmpscope);
      {
        val $scope: _root_.scala.xml.NamespaceBinding = $tmpscope;
        new _root_.scala.xml.Elem(null, "html", _root_.scala.xml.Null, $scope, false, ({
          val $buf = new _root_.scala.xml.NodeBuffer();
          $buf.$amp$plus(new _root_.scala.xml.Text("\n      "));
          $buf.$amp$plus({
            {
              new _root_.scala.xml.Elem(null, "head", _root_.scala.xml.Null, $scope, false, ({
                val $buf = new _root_.scala.xml.NodeBuffer();
                $buf.$amp$plus(new _root_.scala.xml.Text("\n        "));
                $buf.$amp$plus({
                  {
                    var $md: _root_.scala.xml.MetaData = _root_.scala.xml.Null;
                    $md = new _root_.scala.xml.UnprefixedAttribute("content", new _root_.scala.xml.Text("text/html; charset=utf-8"), $md);
                    $md = new _root_.scala.xml.UnprefixedAttribute("http-equiv", new _root_.scala.xml.Text("content-type"), $md);
                    new _root_.scala.xml.Elem(null, "meta", $md, $scope, true)
                  }
                });
                $buf.$amp$plus(new _root_.scala.xml.Text("\n        "));
                $buf.$amp$plus({
                  {
                    new _root_.scala.xml.Elem(null, "title", _root_.scala.xml.Null, $scope, false, ({
                      val $buf = new _root_.scala.xml.NodeBuffer();
                      $buf.$amp$plus(new _root_.scala.xml.Text("Performance profile of Squeryl queries"));
                      $buf
                    }: _*))
                  }
                });
                $buf.$amp$plus(new _root_.scala.xml.Text("\n        "));
                $buf.$amp$plus({
                  {
                    var $md: _root_.scala.xml.MetaData = _root_.scala.xml.Null;
                    $md = new _root_.scala.xml.UnprefixedAttribute("src", new _root_.scala.xml.Text("http://www.google.com/jsapi"), $md);
                    $md = new _root_.scala.xml.UnprefixedAttribute("type", new _root_.scala.xml.Text("text/javascript"), $md);
                    new _root_.scala.xml.Elem(null, "script", $md, $scope, false)
                  }
                });
                $buf.$amp$plus(new _root_.scala.xml.Text("\n        "));
                $buf.$amp$plus({
                  {
                    var $md: _root_.scala.xml.MetaData = _root_.scala.xml.Null;
                    $md = new _root_.scala.xml.UnprefixedAttribute("type", new _root_.scala.xml.Text("text/javascript"), $md);
                    new _root_.scala.xml.Elem(null, "script", $md, $scope, false, ({
                      val $buf = new _root_.scala.xml.NodeBuffer();
                      $buf.$amp$plus(new _root_.scala.xml.Text("\n          google.load(\'visualization\', \'1\', {packages: [\'corechart\']});\n        "));
                      $buf
                    }: _*))
                  }
                });
                $buf.$amp$plus(new _root_.scala.xml.Text("\n        "));
                $buf.$amp$plus({
                  {
                    var $md: _root_.scala.xml.MetaData = _root_.scala.xml.Null;
                    $md = new _root_.scala.xml.UnprefixedAttribute("type", new _root_.scala.xml.Text("text/javascript"), $md);
                    new _root_.scala.xml.Elem(null, "script", $md, $scope, false, ({
                      val $buf = new _root_.scala.xml.NodeBuffer();
                      $buf.$amp$plus(new _root_.scala.xml.Text("\n\n          "));
                      $buf.$amp$plus(Unparsed(drawFunc));
                      $buf.$amp$plus(new _root_.scala.xml.Text("\n\n          function drawVisualization() {\n            "));
                      $buf.$amp$plus(Unparsed(funcCalls(stats)));
                      $buf.$amp$plus(new _root_.scala.xml.Text("\n          }\n\n          google.setOnLoadCallback(drawVisualization);\n        "));
                      $buf
                    }: _*))
                  }
                });
                $buf.$amp$plus(new _root_.scala.xml.Text("\n      "));
                $buf
              }: _*))
            }
          });
          $buf.$amp$plus(new _root_.scala.xml.Text("\n      "));
          $buf.$amp$plus({
            {
              var $md: _root_.scala.xml.MetaData = _root_.scala.xml.Null;
              $md = new _root_.scala.xml.UnprefixedAttribute("style", new _root_.scala.xml.Text("font-family: Arial;border: 0 none;"), $md);
              new _root_.scala.xml.Elem(null, "body", $md, $scope, false, ({
                val $buf = new _root_.scala.xml.NodeBuffer();
                $buf.$amp$plus(new _root_.scala.xml.Text("\n        "));
                $buf.$amp$plus({
                  {
                    var $md: _root_.scala.xml.MetaData = _root_.scala.xml.Null;
                    $md = new _root_.scala.xml.UnprefixedAttribute("style", new _root_.scala.xml.Text("width: 1000px; height: 400px;"), $md);
                    $md = new _root_.scala.xml.UnprefixedAttribute("id", new _root_.scala.xml.Text("chart1"), $md);
                    new _root_.scala.xml.Elem(null, "div", $md, $scope, false)
                  }
                });
                $buf.$amp$plus(new _root_.scala.xml.Text("\n        "));
                $buf.$amp$plus({
                  {
                    var $md: _root_.scala.xml.MetaData = _root_.scala.xml.Null;
                    $md = new _root_.scala.xml.UnprefixedAttribute("style", new _root_.scala.xml.Text("width: 1000px; height: 400px;"), $md);
                    $md = new _root_.scala.xml.UnprefixedAttribute("id", new _root_.scala.xml.Text("chart2"), $md);
                    new _root_.scala.xml.Elem(null, "div", $md, $scope, false)
                  }
                });
                $buf.$amp$plus(new _root_.scala.xml.Text("\n        "));
                $buf.$amp$plus({
                  {
                    var $md: _root_.scala.xml.MetaData = _root_.scala.xml.Null;
                    $md = new _root_.scala.xml.UnprefixedAttribute("style", new _root_.scala.xml.Text("width: 1000px; height: 400px;"), $md);
                    $md = new _root_.scala.xml.UnprefixedAttribute("id", new _root_.scala.xml.Text("chart3"), $md);
                    new _root_.scala.xml.Elem(null, "div", $md, $scope, false)
                  }
                });
                $buf.$amp$plus(new _root_.scala.xml.Text("\n        "));
                $buf.$amp$plus({
                  {
                    var $md: _root_.scala.xml.MetaData = _root_.scala.xml.Null;
                    $md = new _root_.scala.xml.UnprefixedAttribute("style", new _root_.scala.xml.Text("width: 1000px; height: 400px;"), $md);
                    $md = new _root_.scala.xml.UnprefixedAttribute("id", new _root_.scala.xml.Text("chart4"), $md);
                    new _root_.scala.xml.Elem(null, "div", $md, $scope, false)
                  }
                });
                $buf.$amp$plus(new _root_.scala.xml.Text("\n      "));
                $buf
              }: _*))
            }
          });
          $buf.$amp$plus(new _root_.scala.xml.Text("\n    "));
          $buf
        }: _*))
      }
    }
  }
}