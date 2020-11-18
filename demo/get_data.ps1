Install-Module MSOnline -Force

$UserCredential = Get-Credential

Connect-MsolService -Credential $UserCredential

# Get-MsolUser -UserPrincipalName "yjannai@dalet.com" | Select-Object *| Format-List
# Get-MsolUser -UserPrincipalName "yjannai@dalet.com" | Select-Object *| Format-List | Export-CSV c:\o365userlist.csv –NoTypeInformation
# Get-MsolUser -UserPrincipalName "amichaeli@dalet.com"
$david = Get-MsolUser -SearchString "David"
foreach ($d in $david)
{
    $d
}
# Get-MsolAccountSku | Where-Object {$_.SkuPartNumber -eq “ENTERPRISEPACK”} | ForEach-Object {$_.ServiceStatus}
