slice2java config.ice
rmdir /Q /S server\src\gen
mv gen server\src

slice2py config.ice
rmdir /Q /S client\House
rm client\config_ice.py
mv config_ice.py client
mv House client