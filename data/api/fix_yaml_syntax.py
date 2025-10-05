#!/usr/bin/env python3
"""
Fix YAML syntax errors in OpenAPI spec file.
Converts inline JSON-style syntax to proper YAML block format.
"""

import re
import sys

def fix_inline_json_properties(content):
    """
    Convert inline JSON-style property definitions to YAML block format.
    Example: { type: string, minLength: 1 } -> proper YAML block
    """
    
    # Pattern to match inline object definitions with various properties
    # This handles cases like: name: { type: string, minLength: 1, maxLength: 50 }
    pattern = r'^(\s+)(\w+):\s*\{\s*([^}]+)\s*\}$'
    
    lines = content.split('\n')
    fixed_lines = []
    
    for i, line in enumerate(lines):
        match = re.match(pattern, line)
        if match:
            indent = match.group(1)
            property_name = match.group(2)
            properties_str = match.group(3)
            
            # Split the properties
            properties = [p.strip() for p in properties_str.split(',')]
            
            # Build the YAML block format
            fixed_lines.append(f"{indent}{property_name}:")
            for prop in properties:
                if ':' in prop:
                    key, value = prop.split(':', 1)
                    fixed_lines.append(f"{indent}  {key.strip()}: {value.strip()}")
        else:
            fixed_lines.append(line)
    
    return '\n'.join(fixed_lines)

def fix_inline_refs(content):
    """
    Convert inline $ref definitions to YAML block format.
    Example: color: { $ref: '#/components/schemas/HexColor' }
    """
    pattern = r'^(\s+)(\w+):\s*\{\s*\$ref:\s*[\'"]([^\'")]+)[\'"]\s*\}$'
    
    lines = content.split('\n')
    fixed_lines = []
    
    for line in lines:
        match = re.match(pattern, line)
        if match:
            indent = match.group(1)
            property_name = match.group(2)
            ref_path = match.group(3)
            
            fixed_lines.append(f"{indent}{property_name}:")
            fixed_lines.append(f"{indent}  $ref: '{ref_path}'")
        else:
            fixed_lines.append(line)
    
    return '\n'.join(fixed_lines)

def main():
    input_file = 'C:/ReGenesis-A.O.S.P/data/api/my-api-spec-FULL.yaml'
    output_file = 'C:/ReGenesis-A.O.S.P/data/api/my-api-spec-FULL-fixed.yaml'
    
    print(f"Reading {input_file}...")
    with open(input_file, 'r', encoding='utf-8') as f:
        content = f.read()
    
    print("Fixing inline JSON properties...")
    content = fix_inline_json_properties(content)
    
    print("Fixing inline $ref definitions...")
    content = fix_inline_refs(content)
    
    print(f"Writing fixed content to {output_file}...")
    with open(output_file, 'w', encoding='utf-8') as f:
        f.write(content)
    
    print("Done! File has been fixed.")
    print(f"\nNow replace the original file:")
    print(f"  copy {output_file} {input_file}")

if __name__ == '__main__':
    main()
