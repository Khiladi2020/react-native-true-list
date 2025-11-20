import { useMemo } from 'react';
import TrueListViewNativeComponent, {
  type NativeProps,
} from './TrueListViewNativeComponent';

interface TrueListProps extends Omit<NativeProps, 'dataFitter'> {
  items: any[];
  itemTemplate: any;
  dataFitter: Record<string, { key: string; type: string }>;
}

const TrueList = ({
  items,
  itemTemplate,
  dataFitter,
  ...props
}: TrueListProps) => {
  const { newItems, newItemTemplate, newDataFitter } = useMemo(() => {
    return {
      newItems: items?.map((item) => JSON.stringify(item)) ?? [],
      newItemTemplate: JSON.stringify(itemTemplate),
      newDataFitter: JSON.stringify(dataFitter),
    };
  }, [items, itemTemplate, dataFitter]);

  return (
    <TrueListViewNativeComponent
      items={newItems}
      itemTemplate={newItemTemplate}
      dataFitter={newDataFitter}
      {...props}
    />
  );
};

export default TrueList;
