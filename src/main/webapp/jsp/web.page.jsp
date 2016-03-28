<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" pageEncoding="UTF-8"%><%@ page
	contentType="text/html;charset=UTF-8"%>

<tiles:insertDefinition name="homeTemplate">
	<tiles:putAttribute name="body">
		<div class="container">
			<div class="row">
				<div class="col-xs-4 col-sm-4 col-md-4">
					<div class="panel-group" id="accordion">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion"
										href="#collapse1">Collapsible Group 1</a>
								</h4>
							</div>
							<div id="collapse1" class="panel-collapse collapse in">
								<div class="panel-body">
									<table class="table table-striped table-bordered table-list">
										<thead>
											<tr>
												<th>#</th>
												<th class="hidden-xs">Email</th>
												<th>Password</th>
												<th>Phone</th>
											</tr>
										</thead>
										<tbody>
											<tr id="snaptarget" class="ui-widget-header">
												<td align="center"></td>
												<td class="hidden-xs">1</td>
												<td>John Doe</td>
												<td>johndoe@example.com</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-xs-8 col-sm-8 col-md-8">
					<div class="panel panel-default panel-table">
						<div class="panel-heading">
							<h3 class="panel-title">Panel Heading</h3>
						</div>
						<div class="panel-body">
							<table class="table table-striped table-bordered table-list">
								<thead>
									<tr>
										<th>#</th>
										<th class="hidden-xs">Email</th>
										<th>Password</th>
										<th>Phone</th>
									</tr>
								</thead>
								<tbody>
									<tr id="draggable">
										<td align="center"></td>
										<td class="hidden-xs">1</td>
										<td>John Doe</td>
										<td>johndoe@example.com</td>
									</tr>
								</tbody>
							</table>

						</div>
						<div class="panel-footer">
							<div class="row" ng-show="accounts.length">
								<uib-pagination class="pull-right" total-items="totalItem"
									ng-model="curPage" max-size="numPages" class="pagination-md"
									items-per-page="pageSize" boundary-links="true"
									style="margin-right: 20px;" ng-change="pageChanged()"></uib-pagination>
							</div>
						</div>
					</div>

				</div>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>