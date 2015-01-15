<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsps/common/import-common.jspf"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Mapping</title>
		<script src="${pageContext.request.contextPath}/js/mapping-src.js" type="text/javascript" charset="utf-8"></script>
		<script src="${pageContext.request.contextPath}/js/mapping-mid.js" type="text/javascript" charset="utf-8"></script>
		<script src="${pageContext.request.contextPath}/js/mapping-dist.js" type="text/javascript" charset="utf-8"></script>
	</head>
<body>
	<nav class="navbar navbar-default" role="navigation">
	  <div class="container-fluid">
	    <div class="navbar-header">
	      <a class="navbar-brand" href="#">XML Mapping</a>
	    </div>
	    <ul class="nav navbar-nav navbar-right">
	    	<li>
	    		<button type="button" class="btn btn-default navbar-btn">Save</button>
	    	</li>
	    	<li>
	    		<button type="button" class="btn btn-default navbar-btn">Load</button>
	    	</li>
	    </ul>
	  </div>
	</nav>
	<div class="container-fluid">
		<div class="row">
			<div class='col-md-4'>
				<div class='panel panel-primary'>
					<div class="panel-heading">Source XML</div>
					<div class="panel-body">
						<div class="input-group">
				            <input type="text" id="searchInput" class="form-control" placeholder="Search&hellip;">
				            <span class="input-group-btn">
				                <button type="button" id="search" class="btn btn-default">Go</button>
				            </span>
				             <span class="input-group-btn">
				             	<button type="button" ref="app-tree" class="btn btn-default collapse-tree">Collapse</button>
				            </span>
				        </div>
						<ul id='app-tree' class='ztree tree-panel'></ul>
					</div>
				</div>
			</div>
			<div class='col-md-4'>
				<div class='panel panel-primary'>
					<div class="panel-heading">Mapping</div>
					<div class="panel-body">
				        <button type="button" ref="mapping-tree" class="btn btn-default expand-tree">Expand</button>
				        <button type="button" ref="mapping-tree" class="btn btn-default collapse-tree">Collapse</button>
						<ul id='mapping-tree' class='ztree tree-panel'></ul>
					</div>
				</div>
			</div>
			<div class='col-md-4'>
				<div class='panel panel-primary'>
					<div class="panel-heading">Final XML</div>
					<div class="panel-body">
						 <button type="button" ref="dist-tree" class="btn btn-default expand-tree">Expand</button>
				        <button type="button" ref="dist-tree" class="btn btn-default collapse-tree">Collapse</button>
						<ul id='dist-tree' class='ztree tree-panel'></ul>
					</div>
				</div>
				
			</div>
		</div>
		<div class="row">
		  <div class='col-md-4'>
				<div class='panel panel-primary'>
					<div class="panel-heading">Attribute viewer:<label id='nodeName'>Default</label></div>
					<div class="panel-body">
						<ul id='app-attr' class='ztree'></ul>
					</div>
				</div>
		  </div>
		   <div class='col-md-4'>
				<div class='panel panel-primary'>
					<div class="panel-heading">Attribute viewer:<label id='nodeName'>Default</label></div>
					<div class="panel-body">
						<ul id='mapping-attr' class='ztree'></ul>
					</div>
				</div>
		  </div>
		   <div class='col-md-4'>
				<div class='panel panel-primary'>
					<div class="panel-heading">Attribute viewer:<label id='nodeName'>Default</label></div>
					<div class="panel-body">
						<ul id='dist-attr' class='ztree'></ul>
					</div>
				</div>
		  </div>
		</div>
	</div>
</body>
</html>