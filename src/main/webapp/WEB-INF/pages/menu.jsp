<%-- 
    Document   : menu
    Created on : 24-Nov-2021, 12:14:41
    Author     : teodo
--%>

<div class="container">
    <nav class="navbar navbar-expand-md navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="${pageContext.request.contextPath}">NextGenPOS</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarCollapse">
                <ul class="navbar-nav me-auto mb-2 mb-md-0">
                    <li class="nav-item ${activePage eq 'Items' ? 'active' : ''}">
                        <a class="nav-link" href="${pageContext.request.contextPath}/Items">Items</a>
                    </li>
                    <li class="nav-item ${activePage eq 'ProductSpecifications' ? 'active' : ''}">
                        <a class="nav-link" href="${pageContext.request.contextPath}/ProductSpecifications">ProductSpecifications</a>
                    </li>
                    
                </ul>
                <ul class="navbar-nav d-flex">
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/Login">Login</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</div>