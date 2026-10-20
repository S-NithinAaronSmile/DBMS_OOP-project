# PowerShell Build Script for DataForge Employee Management System
Write-Host "Compiling DataForge Java application..." -ForegroundColor Cyan

if (!(Test-Path "bin")) {
    New-Item -ItemType Directory -Path "bin" | Out-Null
}

$sources = Get-ChildItem -Path "src" -Filter "*.java" -Recurse | Select-Object -ExpandProperty FullName
$classpath = ".;lib/mysql-connector-j-8.3.0.jar;bin"

javac -cp $classpath -d bin $sources

if ($LASTEXITCODE -eq 0) {
    Write-Host "Build Successful! Classes compiled to bin/" -ForegroundColor Green
} else {
    Write-Host "Build Failed!" -ForegroundColor Red
}
